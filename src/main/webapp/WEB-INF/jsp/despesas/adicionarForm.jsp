<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:logged-page>
  <jsp:body>
    <div class="flex w-full justify-center mt-5">
      <div class="bg-white shadow w-3/5 p-4">
        <h4 class="text-grey-darker text-center text-xl">Adicionar despesa</h4>
        <t:error-box />
        <form
          action="/controle-financeiro/despesas/adicionar"
          method="post"
          class="px-8 pt-6 pb-8"
        >
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="despesa.descricao"
            >
              Descrição
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="despesa.descricao"
              name="despesa.descricao"
              placeholder="Lanche da tarde"
            />
          </div>
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="despesa.valor"
            >
              Valor
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="despesa.valor"
              name="despesa.valor"
              placeholder="22,22"
            />
          </div>
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="despesa.data"
            >
              Data
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              placeholder="20 de nov de 2018"
              id="despesa.data"
              name="despesa.data"
            />
          </div>
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="despesa.categoria.id"
            >
              Categoria
            </label>
            <div class="inline-block relative w-4/5 mr-5">
              <select
                class="block appearance-none shadow w-full bg-grey-lighter px-4 py-2 pr-4 rounded leading-tight focus:outline-none focus:shadow-outline"
                name="despesa.categoria.id"
                id="despesa.categoria.id"
              >
                <c:forEach var="categoria" items="${categorias}">
                  <option value="${categoria.id}">${categoria.nome}</option>
                </c:forEach>
              </select>
              <div
                class="pointer-events-none absolute pin-y pin-r flex items-center px-2 text-grey-darker"
              >
                <i class="fas fa-arrow-down"></i>
              </div>
            </div>
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
  </jsp:body>
</t:logged-page>