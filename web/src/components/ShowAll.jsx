import inLogo from '../img/inLogo.png';

const ShowAll = function ({ onShow }) {
  return (
    <div id='show-all' >
      <div className="navigation">
        <div className="button">
          <div className="show-all" onClick={onShow} >SHOW ALL URL </div>
        </div>
      </div>
    </div>
  )
};

export default ShowAll;
