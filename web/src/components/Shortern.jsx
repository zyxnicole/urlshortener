import React, { useState } from 'react';
import { postUrl } from '../services';
import { errorMessages } from '../error-messages';
import ErrorMessage from './ErrorMessage';

function Shortener() {

  const [isDisabled, setIsDisabled] = useState(true);
  const [isPending, setIsPending] = useState(false);
  const [longUrl, setlongUrl] = useState('');
  const [newUrl, setNewUrl] = useState('');
  const [preUrl, setPreUrl] = useState('');
  const [show, setShow] = useState(false);
  const [status, setStatus] = useState('');

  function handleChange(event) {
    const value = event.target.value;
    setIsDisabled(!event.target.value);
    setlongUrl(value);
    setShow(false);
    setStatus('');
  }

  function submitUrl() {
    setIsDisabled(true);
    setPreUrl(longUrl);

    postUrl(longUrl)
      .then(data => {
        setNewUrl(data.shortUrl);
        setIsPending(false);
        setlongUrl('');
        setShow(true);
      })
      .catch(err => {
        setStatus(errorMessages[err.error]);
        setIsPending(false);
      });
  }

  return (
    <div id='shortener'>
      <p className='shortener'>Shortener</p>
      <input onChange={handleChange} placeholder="Enter a url" value={longUrl}></input>
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

export default Shortener;