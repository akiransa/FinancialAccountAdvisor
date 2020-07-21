import React from "react";
import axios from "axios";

class APIAccountAdvisor extends React.Component {
  state = {
    posts: [],
    isLoading: true,
    errors: null,
  };
  // Now we're going to make a request for data using axios
  getPosts() {
    const requestOptions = {
      method: "GET",
      headers: {
        Authorization:
          "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHAiOiJkZW1vLWFwcC1lYTE3MzJkNS1jMjAyLTQ0OGEtYmM0OC1mNzhlOGRlM2Y3OTkiLCJvcmciOiJlYTE3MzJkNS1jMjAyLTQ0OGEtYmM0OC1mNzhlOGRlM2Y3OTkuZXhhbXBsZS5vcmciLCJpc3MiOiJuYXR3ZXN0LnVzZWluZmluaXRlLmlvIiwidG9rZW5fdHlwZSI6IkFDQ0VTU19UT0tFTiIsImV4dGVybmFsX2NsaWVudF9pZCI6Ink0cGJkdU1sTG1KZjZ2S1l3b1pTQWhDMXJlcy13WnBoSlV2bFBjX2hOcmM9IiwiY2xpZW50X2lkIjoiMWMxODY2YzctZjFiMi00MGZkLWEzMGUtNmZkOTlkOTVhZGIwIiwibWF4X2FnZSI6ODY0MDAsImF1ZCI6IjFjMTg2NmM3LWYxYjItNDBmZC1hMzBlLTZmZDk5ZDk1YWRiMCIsInVzZXJfaWQiOiIxMjM0NTY3ODkwMTJAZWExNzMyZDUtYzIwMi00NDhhLWJjNDgtZjc4ZThkZTNmNzk5LmV4YW1wbGUub3JnIiwiZ3JhbnRfaWQiOiIxN2Q4NzMzNi1lNDBmLTRhYzYtODA5Yi0zNGUyNjY0ZjhmNjciLCJzY29wZSI6ImFjY291bnRzIG9wZW5pZCIsImNvbnNlbnRfcmVmZXJlbmNlIjoiOTAxYTRkMjEtOWRhNy00MmMyLThhNmItOWU5N2U3Zjg0ZDI2IiwiZXhwIjoxNTk1MDc2MTc2LCJpYXQiOjE1OTUwNzU4NzYsImp0aSI6IjUwNzY0ZGIzLWQzYjUtNDhjOC1iMDFmLWM4MThmYzNiZjg3NCIsInRlbmFudCI6Ik5hdFdlc3QifQ.EOpn6RrC8-2GItBEvOvIDtcSA7mpay319KSJONa898JVOzYbNBdK9p2QM6-8K2j2T0za0XJh60Fvpyk2QQVz9G8G9UWwBGhAPkYUXpKARVHMVrAiCJ62L29IdLwyWK8UibP7yMRAAkwMT0iHaqIztq6_Cvjul3bV1V1TUKt3RHIcDVte_g2kSIDxCHtSWGtj2-3W-v9DTg03gW8ffBx-ytTmCv6WO33C80_YhtiQcAPH4sPI0s98h6QJKBsBN1oFcdeWLXiN-8NWsmzyGLnAz01_v0_jtFTK3qamGEPLf7Xv0nze4Z0O8mN8rNCZArR1kcrq8bB3ItTFl2e8wD_V4w",
      },
    };
    axios
      // This is where the data is hosted
      .get(
        "http://localhost:8080/open-banking/v3.1/aisp/accounts/3e82c5e6-3d02-4dd9-a69b-cc6629734c2d/balances",
        requestOptions
      )
      // Once we get a response and store data, let's change the loading state
      .then((response) => {
        this.setState({
          //posts: response.data.posts,
          posts: response.data.Data.Balance,
          isLoading: false,
        });
      })
      // If we catch any errors connecting, let's update accordingly
      .catch((error) => this.setState({ error, isLoading: false }));
  }
  // Let's our app know we're ready to render the data
  componentDidMount() {
    this.getPosts();
  }
  // Putting that data to use
  render() {
    const { isLoading, posts } = this.state;
    return (
      <React.Fragment>
        <div class="card">
          <div class="card-header">API Financial Account Advisor</div>

          <div>
            {!isLoading ? (
              posts.map((post) => {
                const { CreditDebitIndicator, Amount } = post;
                return (
                  <div>
                    <h2>Credit :{CreditDebitIndicator}</h2>
                    <h2>Amount : {Amount.Amount}</h2>
                    <hr />
                  </div>
                );
              })
            ) : (
              <p>Loading...</p>
            )}
          </div>
          <div class="fixed-bottom">
            Always the Trust advisor for all your financial needs!
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export { APIAccountAdvisor };
