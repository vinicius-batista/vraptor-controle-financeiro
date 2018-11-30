<%@tag description="Logged Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${hasError}">
  <div class="flex bg-white py-4 justify-center">
    <div
      class="bg-red-lightest border-t-4 border-red-dark w-3/5 p-5 shadow"
    >
      <h4 class="text-lg text-bold text-red text-center">${errorMessage}</h4>
    </div>
  </div>
</c:if>