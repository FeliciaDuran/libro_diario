package mx.com.felicia.libro_diario.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.felicia.libro_diario.dal.models.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
	
	Usuarios findByIdUsuario(int idUsuario);
	
//	@Transactional
//	@Modifying
//	@Query("delete from Usuarios u where u.idUsuario = u.idUsuario")
//	void delete(@Param("idUsuario") int idUsuario);
	
}
