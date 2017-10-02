var ajaxUrl = "ajax/snapshots";
var snapshotsDataTable;
var pathsDataTable;

$(function () {
    $.ajaxSetup({cache: false});
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    $.fn.dataTableExt.oSort['ru_date-asc'] = compareDatesAsc;
    $.fn.dataTableExt.oSort['ru_date-desc'] = compareDatesDesc;

    snapshotsDataTable = $("#snapshotsDataTable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "filter": false,
        "columns": [
            {
                "data": "id",
                "visible": false
            },
            {
                "data": "dateTime",
                "type": "ru_date"
            },
            {
                "data": "dir"
            },
            {
                "data": "dirsCount"
            },
            {
                "data": "filesCount"
            },
            {
                "data": "totalSize",
                "render": renderSize
            },
            {
                "render": renderInfoBtn,
                "defaultContent": "",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    $("#addSnapshot").click(function () {
        $.ajax({
            type: "POST",
            url: ajaxUrl,
            data: {
                dir: $("#new-dir").val()
            },
            success: function () {
                $("#new-dir").val("");
                snapshotsDataTable.ajax.reload()
            },
            error: function () {
                snapshotsDataTable.ajax.url(ajaxUrl).load();
            }
        });
    })
});

function initPathsDataTable(url) {
    pathsDataTable = $("#pathsDataTable").DataTable({
        "ajax": {
            "url": url,
            "dataSrc": ""
        },
        "paging": true,
        "lengthChange": false,
        "pageLength": 15,
        "info": false,
        "filter": false,
        "aaSorting": [],
        "columns": [
            {
                "data": "name",
                "orderable": false
            },
            {
                "data": "size",
                "orderable": false,
                "render": renderSize
            }
        ]
    });
}

function showSnapshotInfo(snapshotId) {
    var url = ajaxUrl + "/" + snapshotId + "/paths";
    if (pathsDataTable === undefined) {
        initPathsDataTable(url);
    } else {
        pathsDataTable.ajax.url(url).load();
    }
    var snapshotParams = getDirAndDateTimeById(snapshotsDataTable, snapshotId);
    $("#dir").text(snapshotParams.dir);
    $("#dateTime").text(snapshotParams.dateTime);
    $("#snapshotInfo").modal();
}

function renderSize(data, type, row) {
    if (type === "display") {
        return (data === null) ? "&lt;DIR&gt;" : formatBytes(data);
    }
    return data;
}

function renderInfoBtn(data, type, row) {
    if (type === "display") {
        return "<button class='btn btn-default btn-xs' onclick='showSnapshotInfo(" + row.id + ");'>Файлы</button>";
    }
}