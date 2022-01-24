package com.poscoict.mysite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;

	// 새글
	public boolean addContents(BoardVo vo, int groupNo, int orderNo, int depth) {
		return boardRepository.insert(vo, groupNo, orderNo, depth);
	}

	// 글보기
	public BoardVo getContents(Long no) {
		return boardRepository.findByNo(no);
	}

	// 글 수정 하기 전,
	public BoardVo getContents(Long no, Long userNo) {
		return null;
	}

	// 글 수정
	public Boolean updateContents(BoardVo vo) {
		return boardRepository.update(vo.getNo(), vo.getTitle(), vo.getContent());
	}

	// 글 삭제
	public Boolean deleteContents(Long no, Long userNo) {
		return boardRepository.delete(no);
	}

	// 글 리스트(찾기결과)
	public List<BoardVo> getContentsList(String keyword) {
		List<BoardVo> list = new ArrayList<>();
		list = boardRepository.findAll(keyword);
		return list;
	}

	private boolean increaseGroupOrderNo(BoardVo vo) {
		return false;
	}
}