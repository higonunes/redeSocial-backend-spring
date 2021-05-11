package com.prime;

import com.prime.domain.Curtida;
import com.prime.domain.Follow;
import com.prime.domain.Post;
import com.prime.domain.SystemUser;
import com.prime.enums.Perfil;
import com.prime.repositories.CurtidaRepository;
import com.prime.repositories.FollowRepository;
import com.prime.repositories.PostRepository;
import com.prime.repositories.SystemUserRepository;
import com.prime.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;

@SpringBootApplication
public class PrimeSystemBackEndApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SystemUserRepository systemUserRepository;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CurtidaRepository curtidaRepository;

	@Autowired
	private FollowRepository followRepository;

	public static void main(String[] args) {
		SpringApplication.run(PrimeSystemBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SystemUser systemUser = new SystemUser();
		systemUser.setNome("Higo Nunes");
		systemUser.setEmail("higo@gmail.com");
		systemUser.setHashSenha(bCryptPasswordEncoder.encode("12345"));
		systemUser.addPerfil(Perfil.USUARIO);

		systemUserRepository.save(systemUser);

		SystemUser systemUser2 = new SystemUser();
		systemUser2.setNome("Iones Maria Sousa");
		systemUser2.setEmail("iones@gmail.com");
		systemUser2.setHashSenha(bCryptPasswordEncoder.encode("12345"));
		systemUser2.addPerfil(Perfil.USUARIO);

		systemUserRepository.save(systemUser2);

		SystemUser systemUser3 = new SystemUser();
		systemUser3.setNome("João Sousa");
		systemUser3.setEmail("joao@gmail.com");
		systemUser3.setHashSenha(bCryptPasswordEncoder.encode("12345"));
		systemUser3.addPerfil(Perfil.USUARIO);

		systemUserRepository.save(systemUser3);

		SystemUser systemUser4 = new SystemUser();
		systemUser4.setNome("Influencer");
		systemUser4.setEmail("influencer@gmail.com");
		systemUser4.setHashSenha(bCryptPasswordEncoder.encode("12345"));
		systemUser4.addPerfil(Perfil.USUARIO);

		systemUserRepository.save(systemUser4);

		Post post = new Post();
		post.setLegenda("Primeiro Post");
		post.setImagemNome("375f910397fbc6decbaa710812a2346-7925819158132343893.png");
		post.setDono(systemUser2);
		postRepository.save(post);

		Post post2 = new Post();
		post2.setLegenda("Segundo Post");
		post2.setImagemNome("375f910397fbc6decbaa710812a2346-7925819158132343893.png");
		post2.setDono(systemUser2);
		postRepository.save(post2);

		Post post3 = new Post();
		post3.setLegenda("Terceiro Post");
		post3.setImagemNome("375f910397fbc6decbaa710812a2346-7925819158132343893.png");
		post3.setDono(systemUser4);
		postRepository.save(post3);

		Follow follow = new Follow();
		follow.setDono(systemUser);
		follow.setSeguindo(systemUser2);
		followRepository.save(follow);

		Follow follow2 = new Follow();
		follow2.setDono(systemUser);
		follow2.setSeguindo(systemUser3);
		followRepository.save(follow2);

		Follow follow3 = new Follow();
		follow3.setDono(systemUser);
		follow3.setSeguindo(systemUser4);
		followRepository.save(follow3);

		Curtida c = new Curtida();
		c.setPost(post2);
		c.setDono(systemUser);
		curtidaRepository.save(c);

		Curtida c2 = new Curtida();
		c2.setPost(post2);
		c2.setDono(systemUser2);
		curtidaRepository.save(c2);

		boolean created = new File(System.getProperty("user.dir") + "/images").mkdirs();
	}
}
