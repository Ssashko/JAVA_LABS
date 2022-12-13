<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.omarianchuk.pcinfo.*" %>

<%List<UniqueMotherboard> motherBoardList = (List<UniqueMotherboard>) request.getAttribute("listMotherBoard");%>

<!DOCTYPE html>
<html>
<head>
    <title>MotherBoard</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body>
    <h1 class="title">
        PC information
    </h1>
    <form class="modal-window hide" id="modal-create" action="${pageContext.request.contextPath}/motherboard-servlet" method="POST">
        <h1>Motherboard Create</h1>
        <label  for="vidMotherboardCreate">vid</label>
        <input type="hidden" name="http-method" value="post">
        <input type="text" id="vidMotherboardCreate" name="vidMotherboardCreate">
        <label  for="pidMotherboardUpdate">pid</label>
        <input type="text" id="pidMotherboardCreate" name="pidMotherboardCreate">
        <label  for="vendorMotherboardCreate">vendor</label>
        <input type="text" id="vendorMotherboardCreate" name="vendorMotherboardCreate">
        <input type="submit" name="submitCreate" value="create">
        <button id="closeCreateMotherboard">close</button>
    </form>
    <button id="showCreateMotherboard">Create MotherBoard</button>
    <main>
        <% for(UniqueMotherboard motherboard : motherBoardList) { %>
        <div data-mId="<%=motherboard.getId()%>" class="PC-container">
            <h1>Motherboard: № <%=motherboard.getId()%></h1>
            <div class="modal-wrapper modal-update hide">
                <form class="modal-window" action="${pageContext.request.contextPath}/motherboard-servlet" method="POST">
                    <h1>Motherboard № <%=motherboard.getId()%> Update</h1>
                    <input type="hidden" name="http-method" value="put">
                    <label  for="vidMotherboardUpdate">vid</label>
                    <input value="<%=motherboard.getMotherboard().getVid()%>" type="text" id="vidMotherboardUpdate" name="vidMotherboardUpdate">
                    <label  for="pidMotherboardUpdate">pid</label>
                    <input value="<%=motherboard.getMotherboard().getPid()%>" type="text" id="pidMotherboardUpdate" name="pidMotherboardUpdate">
                    <label  for="vendorMotherboardUpdate">vendor</label>
                    <input value="<%=motherboard.getMotherboard().getVendor()%>" type="text" id="vendorMotherboardUpdate" name="vendorMotherboardUpdate">
                    <input type="hidden" name="idMotherboardUpdate" value="<%=motherboard.getId()%>">
                    <input type="submit" name="submitUpdate" value="update">
                    <button class="closeUpdateMotherboard">close</button>
                </form>
            </div>
            <button class="showUpdateMotherboard">Update</button>

            <form action="${pageContext.request.contextPath}/motherboard-servlet" method="POST">
                <input type="hidden" name="http-method" value="delete">
                <input type="hidden" name="idDelete" value="<%=motherboard.getId()%>">
                <input type="submit" name="submitDelete" value="delete">
            </form>
        <div class="MotherBoard-item-wrapper">
            <div class="MotherBoard-item MotherBoard-item-title">
                <div class="MotherBoard_vid-item">
                        Vendor Id
                    </div>
                <div class="MotherBoard_pid-item">
                        Product Id
                    </div>
                <div class="MotherBoard_vendor-item">
                        Vendor Name
                    </div>
            </div>
            <div data-mId="<%=motherboard.getId()%>" class="MotherBoard-item">
                <div class="MotherBoard_vid-item">
                    <%=motherboard.getMotherboard().getVid()%>
                </div>
                <div class="MotherBoard_pid-item">
                    <%=motherboard.getMotherboard().getPid()%>
                </div>
                <div class="MotherBoard_vendor-item">
                    <%=motherboard.getMotherboard().getVendor()%>
                </div>
            </div>
        </div>
        <div class="cpu-content-wrapper">
            <button data-state="0" class="cpuContentToggleVisibility">Hide</button>
            <div class ="cpu-items">
                <h1>CPU</h1>
                <div class="modal-wrapper modal-plugCPU hide">
                    <form class="modal-window" action="${pageContext.request.contextPath}/motherboard-servlet" method="POST">
                        <h1>Motherboard № <%=motherboard.getId()%> CPU plug</h1>
                        <input type="hidden" name="http-method" value="post-cpu-plug">
                        <label  for="idPluggedCPU">cpu id</label>
                        <input type="text" id="idPluggedCPU" name="idPluggedCPU">
                        <input type="hidden" name="idMotherBoardPlugCPU" value="<%=motherboard.getId()%>">
                        <input type="submit" name="submitUpdate" value="plug">
                        <button class="closePlugCPU">close</button>
                    </form>
                </div>
                <form action="${pageContext.request.contextPath}/motherboard-servlet" method="POST">
                    <input type="hidden" name="http-method" value="post-cpu-unplug">
                    <input type="hidden" name="idMotherBoardPlugCPU" value="<%=motherboard.getId()%>">
                    <input type="submit" name="unPlugCPU" value="unplug">
                </form>
                    <button class="plugCPU">Plug</button>
                    <div class = "cpu-item cpu-item-title">
                        <div class="cpu-item-vid"> Vendor id </div>
                        <div class="cpu-item-pid"> Product id </div>
                        <div class="cpu-item-vendor"> Vendor name </div>
                        <div class="cpu-item-name"> Product Name </div>
                        <div class="cpu-item-sokettype_id">Soket Type </div>
                        <div class="cpu-item-frequency"> Frequency </div>
                        <div class="cpu-item-hyperthreading">Has Hyper Threading </div>
                        <div class="cpu-item-l1cachecapacity">L1 Cache Capacity</div>
                        <div class="cpu-item-l2cachecapacity"> L2 Cache Capacity </div>
                        <div class="cpu-item-l3cachecapacity"> L3 Cache Capacity </div>
                    </div>
                    <%
                        if(motherboard.getMotherboard().getCpu() == null)
                            out.println("no plugged cpu");
                        else {
                    %>
                <div class ="cpu-item">
                    <div class="cpu-item-vid">
                        <%=motherboard.getMotherboard().getCpu().getVid()%>
                    </div>
                    <div class="cpu-item-pid">
                        <%=motherboard.getMotherboard().getCpu().getPip()%>
                    </div>
                    <div class="cpu-item-vendor">
                        <%=motherboard.getMotherboard().getCpu().getVendor()%>
                    </div>
                    <div class="cpu-item-name">
                        <%=motherboard.getMotherboard().getCpu().getName()%>
                    </div>
                    <div class="cpu-item-sokettype_id">
                        <%=motherboard.getMotherboard().getCpu().getSoketType()%>
                    </div>
                    <div class="cpu-item-frequency">
                        <%=motherboard.getMotherboard().getCpu().getFrequency()%>
                    </div>
                    <div class="cpu-item-hyperthreading">
                        <%=motherboard.getMotherboard().getCpu().isHyperThreading()%>
                    </div>
                    <div class="cpu-item-l1cachecapacity">
                        <%=motherboard.getMotherboard().getCpu().getL1CacheCapacity()%>
                    </div>
                    <div class="cpu-item-l2cachecapacity">
                        <%=motherboard.getMotherboard().getCpu().getL2CacheCapacity()%>
                    </div>
                    <div class="cpu-item-l3cachecapacity">
                        <%=motherboard.getMotherboard().getCpu().getL3CacheCapacity()%>
                    </div>
                </div>
                    <%};%>

                </div>
        </div>
            <div class="ram-content-wrapper">
                <button data-state="0" class="ramContentToggleVisibility">Hide</button>
                <div class ="ram-items">
                    <h1>RAM</h1>
                    <div class = "ram-item ram-item-title">
                        <div class="ram-item-vid"> Vendor id </div>
                        <div class="ram-item-pid"> Product id </div>
                        <div class="ram-item-vendor"> Vendor name </div>
                        <div class="ram-item-capacity"> Capacity </div>
                        <div class="ram-item-rank">Rank</div>
                        <div class="ram-item-frequency"> Frequency </div>
                        <div class="ram-item-ramtype">RAM Type </div>
                        <div class="ram-item-ramformfactor">Ram Form Factor</div>
                    </div>

                    <%
                        if(motherboard.getMotherboard().getRams().size() == 0)
                            out.println("no added Ram");
                        else
                            for(Ram ram : motherboard.getMotherboard().getRams())
                            {
                    %>
                    <div class='ram-item'>
                        <div class="ram-vid-item">
                            <%=ram.getVid()%>
                        </div>
                        <div class="ram-pid-item">
                            <%=ram.getPid()%>
                        </div>
                        <div class="ram-vendor-item">
                            <%=ram.getVendor()%>
                        </div>
                        <div class="ram-capacity-item">
                            <%=ram.getCapacity()%>
                        </div>
                        <div class="ram-rank-item">
                            <%=ram.getRank()%>
                        </div>
                        <div class="ram-frequency-item">
                            <%=ram.getFrequency()%>
                        </div>
                        <div class="ram-ramtype-item">
                            <%=ram.getRamType()%>
                        </div>
                        <div class="disk-ramformfactor-item">
                            <%=ram.getRamFormFactor()%>
                        </div>

                    </div>
                    <%}%>
                </div>
            </div>
        <div class="disk-item-wrapper">
            <button data-state="0" class="diskContentToggleVisibility">Hide</button>
            <div class="disk-items">
                <h1>Disk</h1>
                    <div class="disk-item disk-item-title">

                        <div class="Disk-vid-item">
                            Vendor Id
                        </div>
                        <div class="Disk-pid-item">
                            Product Id
                        </div>
                        <div class="Disk-vendor-item">
                            Vendor Name
                        </div>
                        <div class="Disk-capacity-item">
                            Capacity
                        </div>
                        <div class="Disk-interface-item">
                            Interface
                        </div>
                        <div class="Disk-linearspeedofwrite-item">
                            linear speed of write
                        </div>
                        <div class="Disk-linearspeedofread-item">
                            linear speed of read
                        </div>
                        <div class="Disk-randomspeedofwrite-item">
                            random speed of write
                        </div>
                        <div class="Disk-randomspeedofread-item">
                            random speed of read
                        </div>
                    </div>
                    <%
                        if(motherboard.getMotherboard().getDisks().size() == 0)
                            out.println("no added Disk");
                        else
                            for(Disk dsk : motherboard.getMotherboard().getDisks())
                            {
                    %>
                <div class='disk-item'>
                    <div class="disk-vid-item">
                        <%=dsk.getVid()%>
                    </div>
                    <div class="disk-pid-item">
                        <%=dsk.getDid()%>
                    </div>
                    <div class="disk-vendor-item">
                        <%=dsk.getVendor()%>
                    </div>
                    <div class="disk-capacity-item">
                        <%=dsk.getCapacity()%>
                    </div>
                    <div class="disk-interface-item">
                        <%=dsk.getInterface_()%>
                    </div>
                    <div class="disk-linearspeedofwrite-item">
                        <%=dsk.getLinearSpeedOfWrite()%>
                    </div>
                    <div class="disk-linearspeedofread-item">
                        <%=dsk.getLinearSpeedOfRead()%>
                    </div>
                    <div class="disk-randomspeedofwrite-item">
                        <%=dsk.getRandomSpeedOfWrite()%>
                    </div>
                    <div class="disk-randomspeedofread-item">
                        <%=dsk.getRandomSpeedOfRead()%>
                    </div>

                </div>
                <%}%>
            </div>
        </div>
        </div>
        <%}%>
    </main>
    <script src="${pageContext.request.contextPath}/scripts/script.js"></script>
</body>
</html>