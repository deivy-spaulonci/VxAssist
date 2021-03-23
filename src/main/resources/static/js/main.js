
$(function() {
    $('#dgDespesa').datagrid({
        url:'http://localhost:8081/api/despesa/all',
        columns:[[
            {field:'id',title:'Id',width:100},
            {field:'despesa',title:'Despesa',width:100},
            {field:'data',title:'Data',width:100,align:'center'}
        ]]
    });
});

