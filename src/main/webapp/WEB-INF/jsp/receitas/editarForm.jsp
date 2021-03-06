<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:formatNumber value="${receita.valor}" var="valorFormatado" pattern="0.00" />
<fmt:parseDate value="${receita.data}" var="parsedDate" type="date" pattern="yyyy-MM-dd" />
<fmt:formatDate value="${parsedDate}" type="date" var="dataFormatada" />

<t:logged-page>
  <jsp:body>
    <div class="flex w-full justify-center mt-5">
      <div class="bg-white shadow w-3/5 p-4">
        <h4 class="text-grey-darker text-center text-xl">Editar receita</h4>
        <t:error-box />
        <form
          action="/controle-financeiro/receitas/editar"
          method="post"
          class="px-8 pt-6 pb-8"
        >
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="receita.descricao"
            >
              Descrição
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="receita.descricao"
              name="receita.descricao"
              value="${receita.descricao}"
              placeholder="Salario"
            />
          </div>
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="receita.valor"
            >
              Valor
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="receita.valor"
              value="${valorFormatado}"
              name="receita.valor"
              placeholder="22,22"
            />
          </div>
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="receita.data"
            >
              Data
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              placeholder="20 de nov de 2018"
              id="receita.data"
              value="${dataFormatada}"
              name="receita.data"
            />
          </div>
          <input type="hidden" name="receita.id" value="${receita.id}" />
          <div class="flex justify-center">
            <button
              class="w-1/2 bg-blue hover:bg-blue-dark text-white font-bold py-3 mt-3 px-4 rounded focus:outline-none focus:shadow-outline"
              type="submit"
            >
              EDITAR
            </button>
          </div>
        </form>
      </div>
    </div>
  </jsp:body>
</t:logged-page>