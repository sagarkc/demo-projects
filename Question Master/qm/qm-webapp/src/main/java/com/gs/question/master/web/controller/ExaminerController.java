package com.gs.question.master.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
@RequestMapping("/examiner")
@Secured(value="ROLE_EXAMINER")
public class ExaminerController {

}
