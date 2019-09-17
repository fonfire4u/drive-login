package com.syncinator.kodi.login.oauth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.syncinator.kodi.login.KodiLoginCacheManager;
import com.syncinator.kodi.login.model.Pin;
import com.syncinator.kodi.login.oauth.provider.Provider;
import com.syncinator.kodi.login.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AuthorizeController {
	
	@Autowired
	private ApplicationContext context;
	private static final Logger log = LoggerFactory.getLogger(Provider.class);
	
	@RequestMapping("/signin/{pin}")
	public String signin(@PathVariable String pin, Model model, HttpServletRequest request) throws IOException {
		return login(pin, model, request);
	}
	
	@RequestMapping("/authorize")
	public String login(@RequestParam String pin, Model model, HttpServletRequest request) throws IOException {
		Cache<String, Pin> pinCache = KodiLoginCacheManager.getPinCache();
		pin = pin.replace('O', '0');
		Pin storedPin = pinCache.get(pin.toLowerCase());
		if (storedPin != null){
			log.info("******************************Fon.Log : storedPin=" + storedPin.toString());
			log.info("******************************Fon.Log : storedPin.getOwner=" + storedPin.getOwner().toString());
			log.info("******************************Fon.Log : Utils.getRemoteAddress(request)=" + Utils.getRemoteAddress(request).toString());
		}
		//&& storedPin.getOwner().equals(Utils.getRemoteAddress(request))
		if (storedPin != null ) {
			Provider connector = context.getBean(Provider.NAME_PREFIX + storedPin.getProvider(), Provider.class);
			return "redirect:" + connector.authorize(pin);
		}
		log.info("******************************Fon.Log : Not goto google login");
		model.addAttribute("errorMessage", "error.pin.invalid");
		return "index";
	}
}
