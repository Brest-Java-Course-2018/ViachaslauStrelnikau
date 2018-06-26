Ext.define('BizDash.store.GroupDTOstore', {
    extend: 'Ext.data.Store',

     alias: 'store.groupDTOstore',

    requires: [
        'BizDash.model.GroupDTO'
    ],

    autoLoad: true,
    autoSync: true,
    storeId :'groupStore',
    model: 'BizDash.model.GroupDTO',
    proxy: {
        type: 'rest',
        url: 'http://127.0.0.1:8088/groups'
    }
});
