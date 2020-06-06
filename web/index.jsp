<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<%@include file="header.jsp" %>

<body>

<section>
    <div class="m-5">
        <h1 class="tex-center">To-do List</h1>
        <h6 class="float-right px-2">Guillermo Castaneda Echegaray<br>CEN4025C - Module 7 Assignment</h6>
        <a href="new.jsp" class="btn btn-success btn-sm my-2">Add New Item</a>

        <table class="table table-bordered table-hover table-striped table-sm">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="item-id">#</th>
                <th scope="col" class="item-due-date">Due Date</th>
                <th scope="col" class="w-25">Description</th>
                <th scope="col">Details</th>
                <th scope="col" class="item-action">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${listItem}">
                <tr>
                    <th scope="row"><c:out value="${item.id}"/></th>
                    <td><c:out value="${item.itemDueDate}"/></td>
                    <td><c:out value="${item.itemDescription}"/></td>
                    <td><c:out value="${item.itemDetails}"/></td>
                    <td><a href="delete?id=<c:out value='${item.id}' />" class="text-danger">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

</body>
</html>
