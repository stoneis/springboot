package kr.pe.stoneis.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kr.pe.stoneis.board.entity.StoneBoardEntity;
import kr.pe.stoneis.board.entity.StoneBoardFileEntity;

public interface StoneJpaBoardRepository extends CrudRepository<StoneBoardEntity, Integer> {

	List<StoneBoardEntity> findAllByOrderByNumDesc();
	
	@Query("SELECT file FROM StoneBoardFileEntity file WHERE file_num = :fileNum AND num = :num")
	StoneBoardFileEntity findBoardFile(@Param("fileNum") int fileNum, @Param("num") int num);

}
