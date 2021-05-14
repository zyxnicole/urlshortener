import React, { useState } from 'react';
import { getUrl } from '../services';
import { errorMessages } from '../error-messages';
import ErrorMessage from './ErrorMessage';

function Retrieve() {
  const [isDisabled, setIsDisabled] = useState(true);
  const [isPending, setIsPending] = useState(false);
  const [Url, setUrl] = useState('');
  const [newUrl, setNewUrl] = useState(' ');
  const [preUrl, setPreUrl] = useState('');
  const [show, setShow] = useState(false);
  const [status, setStatus] = useState('');

  function handleChange(event) {
    const value = event.target.value;
    setIsDisabled(!event.target.value);
    setUrl(value);
    setShow(false);
    setStatus('');
  }

  function submitUrl() {
    setIsDisabled(true);
    setPreUrl(Url);

    getUrl(Url)
      .then(data => {
        setNewUrl(data.longUrl);
        setIsPending(false);
        setUrl('');
        setShow(true);
      })
      .catch(err => {
        setStatus(errorMessages[err.error]);
        setIsPending(false);
      });
  }

  return (
    <div id='retrieve'>
      <p className='retrieve'>Retrieve</p>
      <input onChange={handleChange} placeholder="Enter a url" value={Url}></input>
      <button onClick={submitUrl} disabled={isPending || isDisabled}>Submit</button>
      <div >
        <span className='input'>Input: </span>
        {show && <span className='pre-url'>{preUrl}</span>}
      </div>
      <div>
        <span className="output"> Url: </span>
        {show && <span className='new-url'>{newUrl}</span>}
      </div>
      {status && <ErrorMessage onChange={status} />}
    </div>
  )
}

export default Retrieve;