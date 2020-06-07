<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@include file="common/header.jsp" %>

<body>

<section>
    <div class="m-5">
        <%@ include file="common/heading.jsp" %>

        <button type="button" class="btn btn-success btn-sm my-2" data-toggle="modal" data-target="#newItemModal">Add
            New Item
        </button>

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

            <c:forEach var="item" items="${items}">
                <tr>

                    <!-- Item id -->
                    <th scope="row" class="text-center"><c:out value="${item.id}"/></th>

                    <!-- Item due date -->
                    <td class="text-center"><c:out value="${item.itemDueDate}"/></td>

                    <!-- Item description -->
                    <td><c:out value="${item.itemDescription}"/></td>

                    <!-- Item details -->
                    <td><c:out value="${item.itemDetails}"/></td>


                    <!-- Actions -->

                    <td class="text-center">

                        <!-- Edit item -->
                        <a href="<%=request.getContextPath()%>/edit?id=<c:out value='${item.id}' />"
                           class="text-primary">Edit</a>
                        <span class="divider">|</span>
                        <!-- Delete item -->
                        <a href="<%=request.getContextPath()%>/confirm?id=<c:out value='${item.id}' />"
                           class="text-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</section>

<!-- Include New Item Modal -->
<%@ include file="new-item.jsp" %>

</body>
</html>
