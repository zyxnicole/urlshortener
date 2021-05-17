function ErrorMessage({ onChange }) {
  const errorMessage = onChange;

  return (
    <div id="status">
      <div className="row">
        <div className="panel panel-primary">
          <div className="panel-heading">
          </div>
        </div>
        <div className="error-notice">
          <div className="oaerror danger">
            <strong>Error</strong> - {errorMessage}
          </div>
        </div>
      </div>
    </div>

  )
}

export default ErrorMessage;
