package com.prime;

import com.prime.domain.Post;
import com.prime.domain.SystemUser;
import com.prime.dto.PostDTO;
import com.prime.enums.Perfil;
import com.prime.repositories.PostRepository;
import com.prime.repositories.SystemUserRepository;
import com.prime.security.UserSS;
import com.prime.service.FeedService;
import com.prime.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.*;

public class FeedResourceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private SystemUserRepository systemUserRepository;

    @Test
    @DisplayName("Deve retornar lista Paginada de posts do usuário logado")
    void deveRetornarListaPaginadaPostsDoUsuarioLogado() {
        FeedService feedService = new FeedService(postRepository, systemUserRepository);
        Set<Perfil> perfils = new HashSet<Perfil>();
        perfils.add(Perfil.USUARIO);
        UserSS userSS = new UserSS(1L, "higo", "higo.sousaa@gmail.com", "12345", perfils);

        SystemUser systemUser = new SystemUser(1L, "higo", "higo.sousaa@gmail.com");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.fromString("DESC"), "data");
        List<Post> list = new ArrayList<>();
        list.add(new Post(1L, "Post 1", "asdasdsad.png", new Date(), systemUser));
        Page<Post> pageList = new PageImpl<Post>(list);

        Page<PostDTO> pageListDTO = pageList.map(x -> new PostDTO(x, userSS));

//        Mockito.when(systemUserRepository.findById(userSS.getId())).thenReturn(Optional.of(systemUser));
 //       Mockito.when(postRepository.feedUsuario(systemUser, pageRequest)).thenReturn(pageList);

        try (MockedStatic<UserService> mb = Mockito.mockStatic(UserService.class)) {
            mb.when(() -> {
                UserService.authenticated();
            }).thenReturn(userSS);



            feedService.listPosts(0, 10, "data", "DESC");

        }


    }
}
