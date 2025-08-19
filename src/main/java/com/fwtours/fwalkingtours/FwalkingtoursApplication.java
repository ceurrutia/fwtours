package com.fwtours.fwalkingtours;

import com.fwtours.fwalkingtours.dto.UsuarioCreateDTO;
import com.fwtours.fwalkingtours.enums.Rol;
import com.fwtours.fwalkingtours.services.UsuarioService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FwalkingtoursApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();

		//propiedades del sistema
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));

		SpringApplication.run(FwalkingtoursApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner init(UsuarioService usuarioService) {
//		return args -> {
//			String email = "admin60@fwtours.com";
//			if (usuarioService.findByEmail(email).isEmpty()) {
//				UsuarioCreateDTO dto = new UsuarioCreateDTO();
//				dto.setEmail(email);
//				dto.setPassword("adminSeguro123");
//				dto.setRol(Rol.ADMIN);
//				dto.setNombreCompleto("Super Admin");
//				dto.setUsername("superadmin60");
//
//				usuarioService.crearUsuario(dto);
//				System.out.println("Usuario SUPERADMIN creado: " + email + " / adminSeguro123");
//			} else {
//				System.out.println("ℹ Ya existe el SUPERADMIN. No se creó uno nuevo.");
//			}
//		};
//	}

}
