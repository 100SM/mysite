package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ViewAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));

		BoardVo vo = new BoardVo();
		BoardDao dao = new BoardDao();
		vo = dao.findByNo(no);
		request.setAttribute("vo", vo);

		// 쿠키 생성
		boolean cookieForBoardHitCountIsExist = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (String.valueOf(no).equals(cookie.getName())) {
					cookieForBoardHitCountIsExist = true;
				}
			}
			if (!cookieForBoardHitCountIsExist) {
				dao.hitCountUp(vo);
				Cookie cookie = new Cookie(String.valueOf(no), String.valueOf(no));
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(24 * 60 * 60);
				response.addCookie(cookie);
			}
		}

		MvcUtil.forward("board/view", request, response);
	}
}