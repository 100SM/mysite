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

public class DeleteAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));

		new BoardDao().delete(no);

		List<BoardVo> list = new ArrayList<>();
		String kwd = request.getParameter("kwd");
		list = new BoardDao().findAll(kwd);
		request.setAttribute("list", list);
		MvcUtil.forward("board/list", request, response);
	}
}