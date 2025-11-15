package io.github.devmeeple.api.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.devmeeple.api.domain.Post;
import io.github.devmeeple.api.request.PostSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static io.github.devmeeple.api.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(post)
                .limit(postSearch.getSize())
                .offset(postSearch.getOffset())
                .orderBy(post.id.desc())
                .fetch();
    }
}
