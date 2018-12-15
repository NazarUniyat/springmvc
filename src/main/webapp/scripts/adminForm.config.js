const config = {
    formID: 'admin-form',
    templateBlockID: 'admin-table',
    formValues: {
        username: 'username',
        email: 'email',
        role: 'account-type'
    },
    apiEndpoint: '/api/adminsearch/data',
    templateFunction: responseItem => {
        const responseProps = [
            'id',
            'accountType',
            'authorities[0].authority',
            'username',
            'email',
            'firstName',
            'lastName'
        ];

        let tr = document.createElement('tr');

        responseProps.forEach(item => {
            let td = document.createElement('td');
            td.innerText = _.get(responseItem, item);
            tr.appendChild(td);
        });

        return tr;
    }
};

const dynamicDataLoader = new DynamicForm(config);