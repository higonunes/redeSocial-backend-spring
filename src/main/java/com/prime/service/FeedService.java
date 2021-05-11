package com.prime.service;

import com.prime.domain.Post;
import com.prime.repositories.PostRepository;
import com.prime.repositories.SystemUserRepository;
import com.prime.security.UserSS;
import com.prime.service.exceptions.AuthorizationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class FeedService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;

    public Page<Post> listPosts(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS userSS = UserService.authenticated();

        if (userSS == null) {
            throw new AuthorizationException();
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return postRepository.feedUsuario(systemUserRepository.findById(userSS.getId()).get(), pageRequest);
    }

    public Post setPost(Post post) {
        UserSS userSS = UserService.authenticated();
        if (userSS == null && userSS.getId() != post.getDono().getId()) {
            throw new AuthorizationException();
        }
        postRepository.save(post);
        return post;
    }

    public String setPostImage(MultipartFile file) throws NoSuchAlgorithmException, IOException {
        UserSS userSS = UserService.authenticated();
        if (userSS == null) {
            throw new AuthorizationException();
        }

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(file.getBytes(), 0, (int) file.getSize());
        String imageName = new BigInteger(1, m.digest()).toString(16) + new Random().nextLong() + ".png";
        Path filepath = Paths.get(System.getProperty("user.dir") + "/images", imageName);
        file.transferTo(filepath);

        return imageName;
    }

    public File getPostImage(String imageName) {
        UserSS userSS = UserService.authenticated();
        if (userSS == null) {
            throw new AuthorizationException();
        }
        return new File(System.getProperty("user.dir") + "/images/" + imageName);
    }
}
