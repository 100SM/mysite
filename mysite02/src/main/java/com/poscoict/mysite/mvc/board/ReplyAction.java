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

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long userNo = Long.parseLong(request.getParameter("userNo"));
		Long no = Long.parseLong(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		BoardVo evo = new BoardDao().findByNo(no);
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(userNo);

		new BoardDao().insert(vo, evo.getGroupNo(), evo.getOrderNo(), evo.getDepth());

		List<BoardVo> list = new ArrayList<>();
		String kwd = request.getParameter("kwd");
		list = new BoardDao().findAll(kwd);
		request.setAttribute("list", list);

		MvcUtil.forward("board/list", request, response);
	}
}