<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

function getSelectedValue(id) {
    return $("#" + id).find("dt a span.value").html();
}

</script>


<dl class="dropdown" style="margin: 0px;">
    <dt><a href="#"><Span><img class="flag" src="assets/images/i18n/lang-en_16x11.png" alt="" />&nbsp;&nbsp;English</Span></a></dt>
    <dd>
        <ul>
            <li><a href="#"><img class="flag" src="assets/images/i18n/lang-en_16x11.png" alt="" />&nbsp;&nbsp;English<span class="value">en</span></a></li>
            <li><a href="#"><img class="flag" src="assets/images/i18n/lang-de_16x11.png" alt="" />&nbsp;&nbsp;Deutsch<span class="value">de</span></a></li>
        </ul>
    </dd>
</dl>