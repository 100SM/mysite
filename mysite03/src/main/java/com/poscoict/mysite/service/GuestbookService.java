package com.poscoict.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.GuestbookRepository;
import com.poscoict.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	GuestbookRepository guestbookrepository;

	// 글 전체 보기
	public List<GuestbookVo> getMessageList() {
		return guestbookrepository.findAll();
	}
	
	// 글 삭제
	public Boolean deleteMessage(Long no, String password) {
		return guestbookrepository.delete(no, password);
	}
	
	// 글 추가
	public Boolean addMessage(GuestbookVo vo) {
		return guestbookrepository.insert(vo);
	}
}