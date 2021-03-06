package com.poscoict.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poscoict.mysite.dto.JsonResult;
import com.poscoict.mysite.service.GuestbookService;
import com.poscoict.mysite.vo.GuestbookVo;

@RestController("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(@RequestBody GuestbookVo vo) {
		boolean result = guestbookService.addMessage(vo);
		return result ? JsonResult.success(vo) : JsonResult.fail("fail");
	}

	@ResponseBody
	@RequestMapping(value = "/list/{sn}", method = RequestMethod.GET)
	public JsonResult list(@PathVariable(value="sn", required = false) Long sn) {
		List<GuestbookVo> list = guestbookService.getMessageList(sn);

		return JsonResult.success(list);
	}

	@ResponseBody
	@RequestMapping("/delete/{no}")
	public JsonResult delete(@PathVariable("no") Long no,
			@RequestParam(value = "password", required = true, defaultValue = "") String password) {
		boolean result = guestbookService.deleteMessage(no, password);
		Long data = result ? no : -1L;
		return JsonResult.success(data);
	}
}
