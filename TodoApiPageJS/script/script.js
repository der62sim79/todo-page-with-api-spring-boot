loadAllTodo();

document.getElementById("save").addEventListener("click", function () {
  saveTodo();
});

function saveTodo() {
  let todo = { name: document.getElementById("myInput").value };

  fetch("http://localhost:8080/todos", {
    method: "POST",
    body: JSON.stringify(todo),
    headers: { "content-type": "application/json" },
  })
    .then(function (data) {
      loadAllTodo();
    })
    .catch(function (err) {
      // There was an error
      console.warn("Something went wrong.", err);
    });
}

function loadAllTodo() {
  fetch("http://localhost:8080/todos")
    .then((response) => response.json())
    .then(function (data) {
      console.log(data);
      let html = "";
      data.forEach((element) => {
        let output = element.done?"checked":"";
        html +=
          "<li class='list-group-item d-flex justify-content-between align-items-center'><input type='checkbox' id='chekOrNot' onclick='check(this," +
          element.id +
          ")' " + output + "> " +
          element.name +
          " <button class='btn btn-primary' onclick='deleteById(" +
          element.id +
          ")'>X</button></li>";
      });
      document.getElementById("todos").innerHTML = html;
    })
    .catch(function (err) {
      // There was an error
      console.warn("Something went wrong.", err);
    });
}

function deleteById(x) {
  let dele = "http://localhost:8080/todos/" + x;
  console.log(dele);

  fetch(dele, {
    method: "DELETE",
  })
    .then(function (data) {
      loadAllTodo();
    })
    .catch(function (err) {
      // There was an error
      console.warn("Something went wrong.", err);
    });
  
}

function check(element, id) {
  let isChecked = element.checked;
  let todo = { done: isChecked };

  let url = "http://localhost:8080/todos/" + id;

  fetch(url, {
    method: "PUT",
    body:JSON.stringify(todo),
    headers: { "content-type": "application/json" },
  })
    .then(function (data) {
      loadAllTodo();
    })
    .catch(function (err) {
      // There was an error
      console.warn("Something went wrong.", err);
    });
  
}

/*var list = document.querySelector('ul');
list.addEventListener('click', function(ev) {
  if (ev.target.tagName === 'li') {
    ev.target.classList.toggle('checked');
  }
}, false);*/
