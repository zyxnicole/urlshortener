import outLogo from '../img/outLogo.png';

const Logout = function({ onLogout }) {
  return (
    <div id='logout' >
      <div class="navigation">
        <div class="button">
        <img src={outLogo} alt='logout'/>
        <div className="logout"  onClick={onLogout} >LOGOUT </div>
        </div>
      </div>
    </div>
  )
};

export default Logout;
