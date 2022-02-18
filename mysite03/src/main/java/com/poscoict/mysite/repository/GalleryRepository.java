package com.poscoict.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<GalleryVo> findAll() {
		return sqlSession.selectList("gallery.findAll");
	}

	public boolean delete(Long no) {
		return 1 == sqlSession.delete("gallery.delete", no);
	}

	public int insert(GalleryVo galleryVo) {
		return sqlSession.insert("gallery.insert", galleryVo);
	}
}
