package com.poscoict.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.SiteVo;

@Repository
public class SiteRepository {

	@Autowired
	private SqlSession sqlSession;

	public boolean update(SiteVo vo) {
		return 1 == sqlSession.update("site.update", vo);
	}

	public SiteVo findByNo(Long no) {
		return sqlSession.selectOne("site.findByNo", no);
	}
}