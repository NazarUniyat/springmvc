const config = {
    formID: 'admin-form',
    tableID: 'admin-table',
    formValues: {
        username: 'username',
        email: 'email',
        role: 'account-type'
    },
    apiEndpoint: 'http://localhost:8080/api/adminsearch/data',
    responseProps:  [
        'id',
        'accountType',
        'authorities[0].authority',
        'username',
        'email',
        'firstName',
        'lastName'
    ]
};

const dynamicDataLoader = new DynamicForm(config);