package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
  private final Map<Long, Post> allPosts = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong();

  public Collection<Post> all() {
    return allPosts.values();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(allPosts.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      long id = idCounter.incrementAndGet();
      post.setId(id);
      allPosts.put(id, post);
    } else if (post.getId() != 0) {
      Long currentId = post.getId();
      allPosts.put(currentId, post);
    }
    return post;
  }

  public void removeById(long id) {
    allPosts.remove(id);
  }
}
