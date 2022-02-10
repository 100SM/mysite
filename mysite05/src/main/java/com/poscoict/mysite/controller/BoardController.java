package com.poscoict.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.security.AuthUser;
import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.util.WebUtil;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String index(@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword, Model model) {
		Map<String, Object> map = boardService.getContentsList(page, keyword);

		model.addAttribute("map", map);

		return "board/index";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo boardVo,
			@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword) {
		boardVo.setUserNo(authUser.getNo());
		boardService.addContents(boardVo);

		return "redirect:/board?p=" + page + "&kwd=" + WebUtil.encodeURL(keyword, "UTF-8");
	}

	@RequestMapping(value = "/update/{no}", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.getContents(no, authUser.getNo());
		model.addAttribute("boardVo", boardVo);

		return "board/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser, BoardVo boardVo,
			@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword) {

		boardVo.setUserNo(authUser.getNo());
		boardService.updateContents(boardVo);

		return "redirect:/board/view/" + boardVo.getNo() + "?p=" + page + "&kwd=" + WebUtil.encodeURL(keyword, "UTF-8");
	}

	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(HttpServletRequest request, @AuthUser UserVo authUser, @PathVariable("no") Long no,
			Model model) {
		model.addAttribute("boardVo", boardService.getContents(no));
		return "board/view";
	}

	@RequestMapping(value = "/reply/{no}")
	public String reply(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.getContents(no);
		boardVo.setOrderNo(boardVo.getOrderNo() + 1);
		boardVo.setDepth(boardVo.getDepth() + 1);

		model.addAttribute("boardVo", boardVo);
		return "board/reply";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@AuthUser UserVo authUser, @PathVariable("no") Long boardNo,
			@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword) {
		boardService.deleteContents(boardNo, authUser.getNo());
		return "redirect:/board?p=" + page + "&kwd=" + WebUtil.encodeURL(keyword, "UTF-8");
	}
}
