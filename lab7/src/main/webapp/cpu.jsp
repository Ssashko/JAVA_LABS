<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="com.omarianchuk.pcinfo.*" %>
<%
    DBService db = new DBService("jdbc:postgresql://localhost:5432/java","postgres","root");
    List<UniqueCPU> CPUList = null;

    try(Connection connect = db.getConnection()) {
        ServiceCPU.setConnection(connect);
        CPUList = ServiceCPU.getCPU();

    } catch (SQLException e) {
        out.println(e.toString());
    }

%>
<!DOCTYPE html>
<html>
<head>
    <title>CPU</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body>
<h1 class="title">
    PC information
</h1>
<form class="modal-window hide" id="modal-create" action="${pageContext.request.contextPath}/cpu-servlet" method="POST">
    <h1>CPU Create</h1>
    <label  for="vidCpuCreate">vid</label>
    <input type="hidden" name="http-method" value="post">
    <input type="text" id="vidCpuCreate" name="vidCpuCreate">
    <label  for="pidCpuCreate">pid</label>
    <input type="text" id="pidCpuCreate" name="pidCpuCreate">
    <label  for="vendorCpuCreate">vendor</label>
    <input type="text" id="vendorCpuCreate" name="vendorCpuCreate">
    <label  for="nameCpuCreate">name</label>
    <input type="text" id="nameCpuCreate" name="nameCpuCreate">
    <label  for="corecountCpuCreate">name</label>
    <input type="text" id="corecountCpuCreate" name="corecountCpuCreate">
    <label  for="sokettypeIdCpuCreate">soket Type</label>
    <input type="text" id="sokettypeIdCpuCreate" name="sokettypeIdCpuCreate">
    <label  for="frequencyCpuCreate">frequency</label>
    <input type="text" id="frequencyCpuCreate" name="frequencyCpuCreate">
    <label  for="hyperthreadingCpuCreate">hyper Threading</label>
    <input type="text" id="hyperthreadingCpuCreate" name="hyperthreadingCpuCreate">

    <label  for="l1cachecapacityCpuCreate">L1 cache capacity</label>
    <input type="text" id="l1cachecapacityCpuCreate" name="l1cachecapacityCpuCreate">

    <label  for="l2cachecapacityCpuCreate">L2 cache capacity</label>
    <input type="text" id="l2cachecapacityCpuCreate" name="l2cachecapacityCpuCreate">

    <label  for="l3cachecapacityCpuCreate">L3 cache capacity</label>
    <input type="text" id="l3cachecapacityCpuCreate" name="l3cachecapacityCpuCreate">

    <input type="submit" name="submitCreate" value="create">
    <button id="closeCreateCPU">close</button>
</form>

<button id="showCreateCPU">Create MotherBoard</button>
<main>
    <% for(UniqueCPU cpu : CPUList) { %>
    <div data-mId="<%=cpu.getId()%>" class="PC-container">
        <h1>CPU: № <%=cpu.getId()%></h1>

        <div class="modal-wrapper modal-update hide">
            <form class="modal-window" action="${pageContext.request.contextPath}/cpu-servlet" method="POST">
                <h1>CPU № <%=cpu.getId()%> Update</h1>

                <input  type="hidden" name="http-method" value="put">
                <label  for="vidCpuUpdate">vid</label>
                <input value="<%=cpu.getCpu().getVid()%>" type="text" id="vidCpuUpdate" name="vidCpuUpdate">
                <label  for="pidCpuUpdate">pid</label>
                <input value="<%=cpu.getCpu().getPip()%>" type="text" id="pidCpuUpdate" name="pidCpuUpdate">
                <label  for="vendorCpuUpdate">vendor</label>
                <input value="<%=cpu.getCpu().getVendor()%>" type="text" id="vendorCpuUpdate" name="vendorCpuUpdate">
                <label  for="nameCpuUpdate">name</label>
                <input value="<%=cpu.getCpu().getName()%>" type="text" id="nameCpuUpdate" name="nameCpuUpdate">
                <label  for="corecountCpuUpdate">core count</label>
                <input value="<%=cpu.getCpu().getCoreCount()%>" type="text" id="corecountCpuUpdate" name="corecountCpuUpdate">
                <label  for="sokettypeIdCpuUpdate">soket Type</label>
                <input value="<%=cpu.getCpu().getSoketType()%>" type="text" id="sokettypeIdCpuUpdate" name="sokettypeIdCpuUpdate">
                <label  for="frequencyCpuUpdate">frequency</label>
                <input value="<%=cpu.getCpu().getFrequency()%>" type="text" id="frequencyCpuUpdate" name="frequencyCpuUpdate">
                <label  for="hyperthreadingCpuUpdate">hyper Threading</label>
                <input value="<%=cpu.getCpu().isHyperThreading()%>" type="text" id="hyperthreadingCpuUpdate" name="hyperthreadingCpuUpdate">

                <label  for="l1cachecapacityCpuUpdate">L1 cache capacity</label>
                <input value="<%=cpu.getCpu().getL1CacheCapacity()%>" type="text" id="l1cachecapacityCpuUpdate" name="l1cachecapacityCpuUpdate">

                <label  for="l2cachecapacityCpuUpdate">L2 cache capacity</label>
                <input value="<%=cpu.getCpu().getL2CacheCapacity()%>" type="text" id="l2cachecapacityCpuUpdate" name="l2cachecapacityCpuUpdate">

                <label  for="l3cachecapacityCpuUpdate">L3 cache capacity</label>
                <input value="<%=cpu.getCpu().getL3CacheCapacity()%>" type="text" id="l3cachecapacityCpuUpdate" name="l3cachecapacityCpuUpdate">

                <input type="hidden" name="idCPUUpdate" value="<%=cpu.getId()%>">
                <input type="submit" name="submitUpdate" value="update">
                <button class="closeUpdateCPU">close</button>
            </form>
        </div>
        <button class="showUpdateCPU">Update</button>

        <form action="${pageContext.request.contextPath}/cpu-servlet" method="POST">
            <input type="hidden" name="http-method" value="delete">
            <input type="hidden" name="idDelete" value="<%=cpu.getId()%>">
            <input type="submit" name="submitDelete" value="delete">
        </form>
        <div class="cpu-content-wrapper">
            <div class ="cpu-items">
                <div class = "cpu-item cpu-item-title">
                    <div class="cpu-item-vid"> Vendor id </div>
                    <div class="cpu-item-pid"> Product id </div>
                    <div class="cpu-item-vendor"> Vendor name </div>
                    <div class="cpu-item-name"> Product Name </div>
                    <div class="cpu-item-corecount">Core Count </div>
                    <div class="cpu-item-sokettype_id">Soket Type </div>
                    <div class="cpu-item-frequency"> Frequency </div>
                    <div class="cpu-item-hyperthreading">Has Hyper Threading </div>
                    <div class="cpu-item-l1cachecapacity">L1 Cache Capacity</div>
                    <div class="cpu-item-l2cachecapacity"> L2 Cache Capacity </div>
                    <div class="cpu-item-l3cachecapacity"> L3 Cache Capacity </div>
                </div>
                <div class ="cpu-item">
                    <div class="cpu-item-vid">
                        <%=cpu.getCpu().getVid()%>
                    </div>
                    <div class="cpu-item-pid">
                        <%=cpu.getCpu().getPip()%>
                    </div>
                    <div class="cpu-item-vendor">
                        <%=cpu.getCpu().getVendor()%>
                    </div>
                    <div class="cpu-item-name">
                        <%=cpu.getCpu().getName()%>
                    </div>
                    <div class="cpu-item-corecount">
                        <%=cpu.getCpu().getCoreCount()%>
                    </div>
                    <div class="cpu-item-sokettype_id">
                        <%=cpu.getCpu().getSoketType()%>
                    </div>
                    <div class="cpu-item-frequency">
                        <%=cpu.getCpu().getFrequency()%>
                    </div>
                    <div class="cpu-item-hyperthreading">
                        <%=cpu.getCpu().isHyperThreading()%>
                    </div>
                    <div class="cpu-item-l1cachecapacity">
                        <%=cpu.getCpu().getL1CacheCapacity()%>
                    </div>
                    <div class="cpu-item-l2cachecapacity">
                        <%=cpu.getCpu().getL2CacheCapacity()%>
                    </div>
                    <div class="cpu-item-l3cachecapacity">
                        <%=cpu.getCpu().getL3CacheCapacity()%>
                    </div>
                </div>

            </div>
        </div>

    </div>
    <%}%>
</main>
<script src="${pageContext.request.contextPath}/scripts/scriptcpu.js"></script>
</body>
</html>