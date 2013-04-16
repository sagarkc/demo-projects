<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div>
	<h2 style="width: 100% !important;">Spring REST support</h2>
</div>

<div>
	<h3>REST: Representational State Transfer</h3>
	<p style="text-align: justify;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	REST
	
	</p>
</div>

<div>
	<h3>Spring</h3>
	<p style="text-align: justify;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The goal of Spring's
		REST support is to make the development of RESTful Web services and
		applications easier. Client-side access to RESTful resources is
		greatly simplified using Spring RestTemplate. RestTemplate follows in
		the footsteps of other template classes in Spring such as
		JdbcTemplate and JmsTemplate. Instead of dealing with a verbose lower
		level API such as Apache Commons HttpClient to create RESTful
		request, RestTemplate provides one liner methods that are purpose
		built for RESTful programming. On the server-side, Spring's REST
		support is based upon Spring's existing annotation based MVC
		framework. (For those interested in the rational for that decision,
		and for not implementing JAX-RS, read Arjen Poutsma's SpringSource
		TeamBlog entry.) With little effort, you can marshall data out of a
		RESTful request using @RequestMapping and @PathVariable annotations
		and return different views as determined by the request's
		Context-Type header. In this chapter we describe all the features of
		Spring's REST support. It is divided into two main two chapters, one
		for the server-side and one for the client-side. For those new to
		Spring's MVC framework, you may want to read through the reference
		documentation on annotation-based controller configuration to
		understand the general programming model.</p>
</div>