const adminForm = document.getElementById('admin-form'),
    adminTableBody = document.getElementById('admin-table'),
    formValues = {
        username: 'username',
        email: 'email',
        role: 'account-type'
    },
    apiEndpoint = 'http://localhost:8080/api/adminsearch/data',
    responseProps = ['name', 'age'];

/**
 * Accepts one level deep object
 * {
 *  key1: value1,
 *  key2: value2
 * }
 */
const encodeQueryData = (data) => {
    let ret = [];
    for (let d in data) {
        if (data[d] !== '') {
            ret.push(encodeURIComponent(d) + '=' + encodeURIComponent(data[d]));
        }
    }
    return ret.join('&');
};

adminForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const params = {};
    for (let key in formValues) {
        params[key] = adminForm.elements[formValues[key]].value
    }
    // Object.keys(formValues).forEach(key => params[key] = form.elements[formValues[key]].value);

    const request = `${apiEndpoint}?${encodeQueryData(params)}`;

    fetch(request).then(response => response.json())
    // Working with admin array
        .then(adminArray => {
            // removing everyting from list
            while (adminTableBody.firstChild) {
                adminTableBody.removeChild(adminTableBody.firstChild);
            }

            adminArray.forEach(admin => {
                /**
                 * Here we have object which is admin
                 * {
                 *      name: 'Nazar',
                 *      age: 21
                 * }
                 */
                let tr = document.createElement('tr');

                responseProps.forEach(item => {
                    let td = document.createElement('td');
                    td.innerText = admin[item]; // admin.name ... admin.age
                    tr.appendChild(td);
                });

                adminTableBody.appendChild(tr);
            })
        })
        .catch(err => {
            console.log('Error', err);
        });
});
