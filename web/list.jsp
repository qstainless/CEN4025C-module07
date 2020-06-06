<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@include file="header.jsp" %>

<body>

<section>
    <div class="m-5">
        <%@ include file="heading.jsp" %>

        <a href="new.jsp" class="btn btn-success btn-sm my-2">Add New Item</a>

        <table class="table table-bordered table-hover table-striped table-sm">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="text-center item-id">#</th>
                <th scope="col" class="text-center item-due-date">Due Date</th>
                <th scope="col" class="w-25">Description</th>
                <th scope="col">Details</th>
                <th scope="col" class="text-center item-action">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${listItem}">
                <tr>
                    <th scope="row" class="text-center"><c:out value="${item.id}"/></th>
                    <td class="text-center"><c:out value="${item.itemDueDate}"/></td>
                    <td><c:out value="${item.itemDescription}"/></td>
                    <td><c:out value="${item.itemDetails}"/></td>
                    <td class="text-center">
                        <a href="edit?id=<c:out value='${item.id}' />" class="text-primary">Edit</a>
                        &nbsp;|&nbsp;
                        <a href="delete?id=<c:out value='${item.id}' />" class="text-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

</body>
</html>
