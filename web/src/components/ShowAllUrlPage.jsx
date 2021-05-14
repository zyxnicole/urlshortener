import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';

function ShowAllUrlPage({ onShowDetail, content }) {

  const columns = [
    { dataField: "shortUrl", text: "Short Url" },
    { dataField: "longUrl", text: "Long Url" },
    { dataField: "count", text: "count" },
    { dataField: "lastCallTime", text: "lastCallTime" },

  ];

  return (
    <div id='content'>
      <BootstrapTable
        className='lists'
        keyField="shortUrl"
        data={content.data}
        columns={columns}
        pagination={paginationFactory()}
      />
      <p>
        <input className='button' type="button" value='Back' onClick={onShowDetail} />
      </p>
    </div>
  )

}

export default ShowAllUrlPage;