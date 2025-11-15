package io.github.devmeeple.api.repository;

import io.github.devmeeple.api.domain.Post;
import io.github.devmeeple.api.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
