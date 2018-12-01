<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:logged-page>
  <jsp:body>
    <div class="flex w-full justify-center mt-5">
      <div class="bg-white shadow w-3/5 p-4">
        <h4 class="text-grey-darker text-center text-xl">
          Adicionar categoria
        </h4>
        <t:error-box />
        <form
          action="/controle-financeiro/categorias/adicionar"
          method="post"
          class="px-8 pt-6 pb-8"
        >
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="categoria.nome"
            >
              Nome
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="categoria.nome"
              name="categoria.nome"
              placeholder="Transporte"
            />
          </div>
          <div class="flex justify-center">
            <button
              class="w-1/2 bg-blue hover:bg-blue-dark text-white font-bold py-3 mt-3 px-4 rounded focus:outline-none focus:shadow-outline"
              type="submit"
            >
              ADICIONAR
            </button>
          </div>
        </form>
      </div>
    </div>
    <div class="flex w-full justify-center mt-5">
      <div class="bg-white shadow w-3/5 p-4">
        <h4 class="text-grey-darker text-center text-xl">Categorias</h4>
        <ul style="list-style-type: none;" class="p-0">
          <c:forEach var="categoria" items="${categorias}">
            <li class="p-3 mt-5">
              <form class="flex justify-center" action="/controle-financeiro/categorias/editar">
                <input
                  class="w-2/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow py-2 px-4 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
                  name="categoria.nome"
                  value="${categoria.nome}"
                />
                <input type="hidden" name="categoria.id" value="${categoria.id}"/>
                <div class="w-1/5 text-lg mt-3">
                  <button
                    type="submit"
                    class="text-grey-darker mx-5"
                  >
                    <i class="fas fa-edit"></i>
                  </button>
                  <a
                    href="/controle-financeiro/categorias/deletar?categoria.id=${categoria.id}"
                    class="text-grey-darker"
                  >
                    <i class="fas fa-trash"></i>
                  </a>
                </div>
              </form>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </jsp:body>
</t:logged-page>