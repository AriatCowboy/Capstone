function Errors({ errors = [] }) {
  if (errors.length === 0) {
    return null;
  }

  return (
    <div class="ui message">
      <h3>Errors...</h3>
      {errors.map((error) => (
        <li key={error}>{error}</li>
      ))}
    </div>
  );
}

export default Errors;
