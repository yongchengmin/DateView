<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页页面</title>
<style type="text/css">
/*翻页*/
.jump{
    margin:0px 0;
    float: right;
    }    
.jump_text{
    float:right;
    margin:0 0 0 5px;
    line-height:33px;
    }
.jump_text input{
    width:40px;
    border:rgba(212,212,212,1.00) 1px solid;
    height:30px;
    line-height:33px;
    background:#fff;}
</style>
<script type="text/javascript">

/*
 * 引用此页面，只需在外面
 */

function goPage(){
    var jumpPage = document.getElementById("jumpPage").value;
    var totalPage = '${totalPages}';
    if(isNaN(jumpPage)){
        alert("请输入数字!");
        return;
    }else if(jumpPage.length==0){
        alert("请输入页码!");
    }else if(jumpPage<=0 || Number(jumpPage)>Number(totalPage)){
        alert("非法的页码【"+jumpPage+"】!");
        document.getElementById("jumpPage").value="";
        return;
    }else{
        var flag = $("input[name='pageNumber']");
        flag.remove();
        $("#myForm").append("<input type='hidden' name='currentPage' value='"+jumpPage+"' />");
        $("#myForm").submit();
    }
} 
function pageTo(pageNumber){
    var jumpPage=1;
    if(pageNumber==-1){
        var curpage='${pageNumber}';
        jumpPage=Number(curpage)-1;
    }else if(pageNumber==-2){
        var curpage='${pageNumber}';
        jumpPage=Number(curpage)+1;
    }else{
        jumpPage=Number(pageNumber);
    }
    var flag = $("input[name='pageNumber']");
    flag.remove();
    $("#myForm").append("<input type='hidden' name='currentPage' value='"+jumpPage+"' />");
    $("#myForm").submit();
}
</script>
</head>
<body>
<!--  分页页码     -->
                    <div style="height: 400px;">
                    
                    </div>
                    <hr>
                    <nav>
                        <ul class="pagination">
                        
                            <!-- 上一页  -->
                            <!-- 当当前页码为1时，隐藏上一页按钮  -->
                            <li <c:if test="${currentPage==1 }">class="disabled"</c:if>>
                            <!-- 当当前页码不等于1时，跳转到上一页  -->
                                <a 
                                <c:if test="${currentPage==1 }">href="javaScript:void(0)"</c:if>
                                <c:if test="${currentPage!=1 }">href="javaScript:pageTo('${currentPage-1 }')"</c:if>
                                >上一页</a>
                            </li>
                            
                            <!-- 页码  -->
                            <!-- 当总页数小于等于7时，显示页码1...7页 -->
                            <c:if test="${totalPages<=7}">
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <li <c:if test="${currentPage==i }">class="active"</c:if>>
                                        <a
                                        href="javaScript:pageTo('${i}')">${i}</a>
                                    </li>
                                </c:forEach>
                            </c:if>
                            <!-- 当总页数大于7时 -->
                            <c:if test="${totalPages>7}">
                                <!-- 当前页数小于等于4时，显示1到5...最后一页 -->
                                <c:if test="${currentPage<=4}">
                                    <c:forEach begin="1" end="5" var="i">
                                        <li <c:if test="${currentPage==i }">class="active"</c:if>>
                                            <a
                                            href="javaScript:pageTo('${i}')">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <li><a href="#">...</a></li>
                                    <li
                                        <c:if test="${currentPage==totalPages }">class="active"</c:if>>
                                        <a
                                        href="javaScript:pageTo('${totalPages}')">${totalPages}</a>
                                    </li>
                                </c:if>
                                <!-- 当前页数大于4时，如果当前页小于总页码书-3，则显示1...n-1,n,n+1...最后一页 -->
                                <c:if test="${currentPage>4}">
                                    <c:if test="${currentPage<totalPages-3}">
                                        <li><a
                                            href="javaScript:pageTo('${1}')">${1}</a>
                                        </li>
                                        <li><a href="#">...</a></li>
                                        <c:forEach begin="${currentPage-1 }" end="${currentPage+1 }"
                                            var="i">
                                            <li <c:if test="${currentPage==i }">class="active"</c:if>>
                                                <a
                                                href="javaScript:pageTo('${i}')">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li><a href="#">...</a></li>
                                        <li
                                            <c:if test="${currentPage==totalPages }">class="active"</c:if>>
                                            <a
                                            href="javaScript:pageTo('${totalPages}')">${totalPages}</a>
                                        </li>
                                    </c:if>
                                </c:if>
                                <!-- 当前页数大于4时，如果当前页大于总页码书-4，则显示1...最后一页-3，最后一页-2，最后一页-1，最后一页 -->
                                <c:if test="${currentPage>totalPages-4}">
                                    <li><a
                                        href="javaScript:pageTo('${1}')">${1}</a>
                                    </li>

                                    <li><a href="#">...</a></li>
                                    <c:forEach begin="${totalPages-3 }"
                                        end="${totalPages }" var="i">
                                        <li <c:if test="${currentPage==i }">class="active"</c:if>>
                                            <a
                                            href="javaScript:pageTo('${i}')">${i}</a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </c:if>
                            <!-- 下一页  -->
                            <!-- 当当前页码为最后一页或者最后一页为0时，隐藏下一页按钮
                                               当当前页码不等于总页码时，跳转下一页  -->
                            <li
                                <c:if test="${currentPage==totalPages || totalPages==0}">class="disabled"</c:if>>
                                <a
                                <c:if test="${currentPage==totalPages || totalPages==0 }">href="javaScript:void(0)"</c:if>
                                <c:if test="${currentPage!=totalPages }">href="javaScript:pageTo('${currentPage+1 }')"</c:if>>下一页</a>
                            </li>
                        </ul>
                        
                        <!-- 跳转页 -->
                        <div class="jump">
                            <span class="jump_text">共有${totalPages }页,${totalElements }条记录,跳到
                                <input type="text" name="jumpPage"
                                id="jumpPage"
                                onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');">页
                                <button type="button" class="btn btn-primary btn-xs"
                                    onclick="goPage()">GO</button>
                            </span>
                        </div>
                    </nav>

            <div style="clear: both;"></div>
       <hr>
       totalPages:共有多少页；totalElements：共有有多少条记录；currentPage：第几页
</body>
</html>