function ErrorMessage({ onChange }) {
  const errorMessage = onChange;

  return (
    <div id="status">
      <div class="row">
        <div class="panel panel-primary">
          <div class="panel-heading">
          </div>
        </div>
        <div class="error-notice">
          <div class="oaerror danger">
            <strong>Error</strong> - {errorMessage}
          </div>
        </div>
      </div>
    </div>

  )
}

export default ErrorMessage;