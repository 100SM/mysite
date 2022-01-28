package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Long userNo = Long.parseLong(request.getParameter("userNo"));

		BoardVo vo = new BoardVo();

		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(userNo);

		new BoardDao().insert(vo, 0, 0, 0);

		List<BoardVo> list = new ArrayList<>();
		String kwd = request.getParameter("kwd");
		list = new BoardDao().findAll(kwd);
		request.setAttribute("list", list);

		MvcUtil.forward("board/list", request, response);
	}
}