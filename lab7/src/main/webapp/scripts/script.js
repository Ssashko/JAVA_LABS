function blockHide(e,wrapper)
{
    if (e.target.dataset.state === "0") {
        e.target.innerHTML = "Show";
        wrapper.classList.add("hide");
        e.target.dataset.state = "1";
    } else if (e.target.dataset.state === "1")
    {
        e.target.innerHTML = "Hide";
        wrapper.classList.remove("hide");
        e.target.dataset.state = "0";
    }
}

document.querySelector("main")
    .addEventListener("click", function (e) {
        if(!e.target.classList.contains("cpuContentToggleVisibility"))
            return;
        let wrapper = e.target.parentNode.querySelector(".cpu-items");
        blockHide(e,wrapper);
    });

document.querySelector("main")
    .addEventListener("click", function (e) {
        if(!e.target.classList.contains("diskContentToggleVisibility"))
            return;
        let wrapper = e.target.parentNode.querySelector(".disk-items");
        blockHide(e,wrapper);
    })

document.querySelector("main")
    .addEventListener("click", function (e) {
        if(!e.target.classList.contains("ramContentToggleVisibility"))
            return;
        let wrapper = e.target.parentNode.querySelector(".ram-items");
        blockHide(e,wrapper);
    })

document.querySelector("main")
    .addEventListener("click", function (e) {
        if(!e.target.classList.contains("showUpdateMotherboard"))
            return;
        let wrapper = e.target.parentNode.querySelector(".modal-update");
        wrapper.classList.remove("hide");
    });

document.querySelector("main")
    .addEventListener("click", function (e) {
        if(!e.target.classList.contains("closeUpdateMotherboard"))
            return;
        e.preventDefault();
        let wrapper = e.target.parentNode.parentNode;
        wrapper.classList.add("hide");
    });

document.querySelector("#showCreateMotherboard")
    .addEventListener("click", function (e) {
        document.getElementById("modal-create").classList.remove("hide");
    });

document.querySelector("#closeCreateMotherboard")
    .addEventListener("click", function (e) {
        e.preventDefault();
        document.getElementById("modal-create").classList.add("hide");
    });

document.querySelector("main")
    .addEventListener("click", function (e) {
        if(!e.target.classList.contains("plugCPU"))
            return;
        e.preventDefault();
        let wrapper = e.target.parentNode.querySelector(".modal-plugCPU");
        wrapper.classList.remove("hide");
    });

document.querySelector("main")
    .addEventListener("click", function (e) {
        if(!e.target.classList.contains("closePlugCPU"))
            return;
        e.preventDefault();
        let wrapper = e.target.parentNode.parentNode;
        wrapper.classList.add("hide");
    });