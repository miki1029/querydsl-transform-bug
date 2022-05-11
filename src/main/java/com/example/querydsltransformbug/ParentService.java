package com.example.querydsltransformbug;

import static com.example.querydsltransformbug.QParent.parent;
import static com.querydsl.core.group.GroupBy.groupBy;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParentService extends QuerydslRepositorySupport {
    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        super(Parent.class);
        this.parentRepository = parentRepository;
    }

    @Transactional
    public void create() {
        Parent p1 = new Parent(1L, "p1", new ArrayList<>());
        Child c1 = new Child(10L, "c1", p1);
        p1.getChildren().add(c1);

        parentRepository.save(p1);
    }

    @Transactional(readOnly = true)
    public Map<Long, Parent> getWithoutFetchJoin(Long id) {
        return from(parent)
                .leftJoin(parent.children)
                .where(parent.id.eq(id))
                .transform(groupBy(parent.id).as(parent));
    }

    @Transactional(readOnly = true)
    public Map<Long, Parent> getWithFetchJoin(Long id) {
        return from(parent)
                .leftJoin(parent.children)
                .fetchJoin()
                .where(parent.id.eq(id))
                .distinct()
                .transform(groupBy(parent.id).as(parent));
    }
}
