async function solve(){
    async function loadStudents(){
    let req = await fetch('http://localhost:3030/jsonstore/collections/students');
    let data = await req.json();
    let students = Object.entries(data);
    let results = document.getElementById('results');
    let tbody = document.getElementById('tbody');
    tbody.innerHTML = '';
   
    students.map((x)=>{
        let tr = document.createElement('tr');
        let fName = document.createElement('th');
        let sName = document.createElement('th');
        let fNumber = document.createElement('th');
        let grade = document.createElement('th');
        let num = Number(x[1].grade);
        fName.textContent = x[1].firstName;
        sName.textContent = x[1].lastName;
        fNumber.textContent = x[1].facultyNumber;
        grade.textContent = num.toFixed(2);
        tr.appendChild(fName);
        tr.appendChild(sName);
        tr.appendChild(fNumber);
        tr.appendChild(grade);
        tbody.appendChild(tr);
    })


    console.log(students);
    }
    loadStudents();

    let form = document.getElementById('form');
    let inputs = document.querySelectorAll('input');
    let firstName = inputs[0];
    let lastName = inputs[1];
    let facultyNumber = inputs[2];
    let grade = inputs[3];
    form.addEventListener('submit',async (e)=>{
        e.preventDefault();
        if (!firstName.value||!lastName.value||!facultyNumber.value||!grade.value){
            clearFields();
            return alert('All fields are mandatory!');
        }
        
        if(isNaN(Number(grade.value))){
            clearFields();
            return alert('Grade must be a number!');
        }
        let student = {
            firstName: firstName.value,
            lastName: lastName.value,
            facultyNumber: facultyNumber.value,
            grade: grade.value
        }
        await fetch('http://localhost:3030/jsonstore/collections/students',{
            method: 'post',
            body: JSON.stringify(student)
        })
        clearFields();
        function clearFields(){
            firstName.value = '';
            lastName.value = '';
            facultyNumber.value = '';
            grade.value = '';
        }
        loadStudents();
    })
}

solve();