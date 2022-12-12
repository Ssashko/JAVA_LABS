document.querySelector("main")
    .addEventListener("click", function (e) {
        if(!e.target.classList.contains("showUpdateCPU"))
            return;
        let wrapper = e.target.parentNode.querySelector(".modal-update");
        wrapper.classList.remove("hide");
    });

document.querySelector("main")
    .addEventListener("click", function (e) {
        if(!e.target.classList.contains("closeUpdateCPU"))
            return;
        e.preventDefault();
        let wrapper = e.target.parentNode.parentNode;
        wrapper.classList.add("hide");
    });

document.querySelector("#showCreateCPU")
    .addEventListener("click", function (e) {
        document.getElementById("modal-create").classList.remove("hide");
    });

document.querySelector("#closeCreateCPU")
    .addEventListener("click", function (e) {
        e.preventDefault();
        document.getElementById("modal-create").classList.add("hide");
    });