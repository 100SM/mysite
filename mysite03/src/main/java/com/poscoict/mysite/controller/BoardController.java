package com.poscoict.mysite.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping({ "", "/list" })
	public String list(Model model,
			@RequestParam(value = "keyword", required = true, defaultValue = "") String keyword) {
		List<BoardVo> list = boardService.getContentsList(keyword);
		model.addAttribute("list", list);
		return "board/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpSession session, Model model) {
		/* access controller */
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, BoardVo boardVo) {
		/* access controller */
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		boardService.addContents(boardVo, 0, 0, 0);
		return "redirect:/board";
	}

	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(HttpSession session, @PathVariable("no") Long no, Model model) {
		/* access controller */
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		model.addAttribute("vo", boardService.getContents(no));
		return "board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpSession session, BoardVo boardVo) {
		/* access controller */
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		boardService.updateContents(boardVo);
		return "redirect:/board";
	}

	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(HttpSession session, @PathVariable("no") Long no, Model model) {
		model.addAttribute("vo", boardService.getContents(no));
		return "board/view";
	}

	@RequestMapping(value = "/reply/{no}", method = RequestMethod.GET)
	public String reply(HttpSession session, @PathVariable("no") Long no, Model model) {
		/* access controller */
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		model.addAttribute("vo", boardService.getContents(no));
		return "board/reply";
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(HttpSession session, BoardVo boardVo) {
		/* access controller */
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		boardService.addContents(boardVo, boardVo.getGroupNo(), boardVo.getOrderNo(), boardVo.getDepth());
		return "redirect:/board";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(HttpSession session, @PathVariable("no") Long no, Model model) {
		/* access controller */
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		boardService.deleteContents(no, authUser.getNo());
		return "redirect:/board";
	}
}
